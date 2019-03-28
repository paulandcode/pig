package com.paulandcode.shiro.credential;

import com.paulandcode.system.entity.User;
import com.paulandcode.utils.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import static com.paulandcode.common.Constant.HASH_ALGORITHM_NAME;
import static com.paulandcode.common.Constant.HASH_ITERATIONS;
import static com.paulandcode.common.Constant.STORED_CREDENTIALS_HEX_ENCODED;

/**
 * 带有重试密码次数限制的凭证匹配器
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/20 23:22
 */
public class PasswordHelper {
    /**
     * 随机数生成器
     */
    private static final RandomNumberGenerator SECURE_RANDOM_NUMBER_GENERATOR = new SecureRandomNumberGenerator();

    /**
     * 给密码加密, 此处加密方式应该与登录时密码验证一致
     *
     * @param user 用户
     */
    public static void encryptPassword(User user) {
        if (user != null) {
            String password = user.getPassword();
            if (!StringUtils.isEmpty(password)) {
                String salt;
                String newPassword;
                ByteSource byteSource = SECURE_RANDOM_NUMBER_GENERATOR.nextBytes();
                // 16进制字符串加密用toHex()方法，base64加密用toBase64()方法
                if (STORED_CREDENTIALS_HEX_ENCODED) {
                    salt = byteSource.toHex();
                    newPassword = new SimpleHash(HASH_ALGORITHM_NAME, password, ByteSource.Util.bytes(salt),
                            HASH_ITERATIONS).toHex();
                } else {
                    salt = byteSource.toBase64();
                    newPassword = new SimpleHash(HASH_ALGORITHM_NAME, password, ByteSource.Util.bytes(salt),
                            HASH_ITERATIONS).toBase64();
                }
                user.setSalt(salt);
                user.setPassword(newPassword);
            }
        }
    }
}
