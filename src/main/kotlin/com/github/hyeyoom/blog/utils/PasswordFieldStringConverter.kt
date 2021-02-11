package com.github.hyeyoom.blog.utils

import javax.persistence.AttributeConverter
import javax.persistence.Convert

@Convert
class PasswordFieldStringConverter : AttributeConverter<String, String> {

    override fun convertToDatabaseColumn(attribute: String): String {
        return EncryptionHashUtil.generateHash(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): String {
        return dbData
    }
}