package cn.ryan.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.lang.Nullable;

import cn.ryan.utils.Encrypt;
import cn.ryan.utils.RyanUtil;

public class JDBCPwdEncryptor extends PropertySourcesPlaceholderConfigurer {

    private static final String LIKE_JDBC_PASSWORD_KEY = ".password";
    private static final String LIKE_LADP_PASSWORD_KEY = "_PWD";
    private static final String JDBC_ENCRYPT_KEY = "encrypt";

    private static Logger log = LogManager.getLogger(JDBCPwdEncryptor.class);

    @Nullable
    private MutablePropertySources propertySources;

    @Nullable
    private Environment environment;

    /**
     * Customize the set of {@link PropertySources} to be used by this configurer.
     * <p>Setting this property indicates that environment property sources and
     * local properties should be ignored.
     * @see #postProcessBeanFactory
     */
    public void setPropertySources(PropertySources propertySources) {
        this.propertySources = new MutablePropertySources(propertySources);
    }

    /**
     * {@code PropertySources} from the given {@link Environment}
     * will be searched when replacing ${...} placeholders.
     * @see #setPropertySources
     * @see #postProcessBeanFactory
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Processing occurs by replacing ${...} placeholders in bean definitions by resolving each
     * against this configurer's set of {@link PropertySources}, which includes:
     * <ul>
     * <li>all {@linkplain org.springframework.core.env.ConfigurableEnvironment#getPropertySources
     * environment property sources}, if an {@code Environment} {@linkplain #setEnvironment is present}
     * <li>{@linkplain #mergeProperties merged local properties}, if {@linkplain #setLocation any}
     * {@linkplain #setLocations have} {@linkplain #setProperties been}
     * {@linkplain #setPropertiesArray specified}
     * <li>any property sources set by calling {@link #setPropertySources}
     * </ul>
     * <p>If {@link #setPropertySources} is called, <strong>environment and local properties will be
     * ignored</strong>. This method is designed to give the user fine-grained control over property
     * sources, and once set, the configurer makes no assumptions about adding additional sources.
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (this.propertySources == null) {
            this.propertySources = new MutablePropertySources();
            if (this.environment != null) {
                this.propertySources.addLast(
                        new PropertySource<Environment>(ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME, this.environment) {
                            @Override
                            @Nullable
                            public String getProperty(String key) {
                                return this.source.getProperty(key);
                            }
                        });
            }
            try {
                PropertySource<?> localPropertySource = new PropertiesPropertySource(
                        LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME, mergeProperties());
                if (this.localOverride) {
                    this.propertySources.addFirst(localPropertySource);
                } else {
                    this.propertySources.addLast(localPropertySource);
                }
            } catch (IOException ex) {
                throw new BeanInitializationException("Could not load properties", ex);
            }
        }

        // 解密此对象
        PropertySource<?> ps = this.propertySources.get(LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME);
        if (ps != null) {
            Properties props = (Properties) ps.getSource();
            String encryptPwd = "";
            String encryptPwdKey = "";
            String deEncrypt = "";
            Map<String, String> encryptPwd_encryptPwdKey = new HashMap<String, String>();
            try {
                Set<Object> keyset = props.keySet();
                if (keyset != null) {
                    for (Object key : keyset) {
                        if (key.toString().endsWith(LIKE_JDBC_PASSWORD_KEY)
                                || key.toString().endsWith(LIKE_LADP_PASSWORD_KEY)) {
                            encryptPwdKey = key.toString();
                            encryptPwd = props.getProperty(key.toString());
                            encryptPwd_encryptPwdKey.put(encryptPwdKey, encryptPwd);
                        }
                    }
                }
                String encrypt = props.getProperty(JDBC_ENCRYPT_KEY);
                if (!RyanUtil.isEmpty(encrypt) && encrypt.equalsIgnoreCase("true")) {
                    log.info("The current jdbc password is encrypted.");
                    for (String key : encryptPwd_encryptPwdKey.keySet()) {
                        encryptPwd = encryptPwd_encryptPwdKey.get(key);
                        if (!RyanUtil.isEmpty(encryptPwd)) {
                            deEncrypt = Encrypt.decodeString(encryptPwd);
                            log.info("Decrypted jdbc password [" + key + ":" + encryptPwd + "] successful.");
                            props.setProperty(key, deEncrypt);
                        }
                    }
                    // 增加AES解密方式
                } else if (!RyanUtil.isEmpty(encrypt) && encrypt.equalsIgnoreCase("aes")) {
                    log.info("The current jdbc password is encrypted.");
                    for (String key : encryptPwd_encryptPwdKey.keySet()) {
                        encryptPwd = encryptPwd_encryptPwdKey.get(key);
                        if (!RyanUtil.isEmpty(encryptPwd)) {
                            deEncrypt = Encrypt.decryptAES(encryptPwd);
                            log.info("AES Decrypted jdbc password [" + key + ":" + encryptPwd + "] successful.");
                            props.setProperty(key, deEncrypt);
                        }
                    }
                }
                // 
                String host = props.getProperty("jdbc.url");
                host = host.replace("localhost", "192.168.1.100");
                props.setProperty("jdbc.url", host);
            } catch (Exception e) {
                log.warn("Decryptied jdbc password failure： [" + encryptPwd + "]", e);
            }
        }
        // 调用supper
        super.processProperties(beanFactory, new PropertySourcesPropertyResolver(this.propertySources));
        //this.appliedPropertySources = this.propertySources;
    }
}
