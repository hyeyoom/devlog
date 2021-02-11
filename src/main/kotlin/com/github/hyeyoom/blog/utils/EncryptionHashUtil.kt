package com.github.hyeyoom.blog.utils

import java.math.BigInteger
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class EncryptionHashUtil {
    companion object {
        private const val ITERATION: Int = 1000
        private const val SALT_LENGTH: Int = 16
        private const val RADIX: Int = 16
        private const val PBKDF2_HMAC_SHA1: String = "PBKDF2WithHmacSHA1"
        private const val SECURE_RND: String = "SHA1PRNG"
        private const val DELIMITER: String = ":"

        /**
         * @param plain Plain text to be hashed
         */
        fun generateHash(plain: String): String {
            val charSeq = plain.toCharArray()
            val salt = getSalt()

            val spec = PBEKeySpec(charSeq, salt, ITERATION, 64 * 8)
            val skf = SecretKeyFactory.getInstance(PBKDF2_HMAC_SHA1)
            val hash = skf.generateSecret(spec).encoded
            return "$ITERATION:${toHex(salt)}:${toHex(hash)}"
        }

        /**
         * @param plain Plain text to check
         * @param encoded Encoded text to be compared
         */
        fun checkEncryptionValidation(plain: String, encoded: String): Boolean {
            val parts = encoded.split(DELIMITER)
            val iteration = parts[0].toInt()
            val salt = fromHex(parts[1])
            val hash = fromHex(parts[2])

            val spec = PBEKeySpec(plain.toCharArray(), salt, iteration, hash.size * 8)
            val skf = SecretKeyFactory.getInstance(PBKDF2_HMAC_SHA1)
            val testHash = skf.generateSecret(spec).encoded

            var diff = hash.size xor testHash.size
            for (i in hash.indices) {
                diff = diff or (hash[i].toInt() xor testHash[i].toInt())
            }
            return diff == 0
        }

        private fun getSalt(): ByteArray {
            val sr = SecureRandom.getInstance(SECURE_RND)
            val salt = ByteArray(SALT_LENGTH)
            sr.nextBytes(salt)
            return salt
        }

        private fun toHex(array: ByteArray): String {
            val bi = BigInteger(1, array)
            val hex = bi.toString(RADIX)
            val paddingLength = (array.size * 2) - hex.length
            return if (paddingLength > 0) "0".repeat(paddingLength) + hex else hex
        }

        private fun fromHex(hex: String): ByteArray {
            val bytes = ByteArray(hex.length / 2)
            for (i in bytes.indices) {
                bytes[i] = Integer.parseInt(hex.substring(2 * i, 2 * i + 2), RADIX).toByte()
            }
            return bytes
        }
    }
}