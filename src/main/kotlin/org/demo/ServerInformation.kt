package org.demo

import java.io.File
import java.nio.charset.Charset

class ServerInformation {

    fun loadServerInfo(contentType: String) = loadContent(contentType)

    private fun loadContent(contentType: String): String {
        return when {
            contentType == "application/json" -> relayInfo()
            else -> loadFromFile("src/main/resources/public/index.html")
        }
    }


    private fun relayInfo(): String {
        return """
            {
              "name": "lnwza007",
              "icon": "https://image.nostr.build/fc4a04e980020ed876874fa0142edd9fc22774efa8fa067f96285f2f44965e38.jpg",
              "description": "7iPsOdH85TybKDT6s0vpw",
            }
        """.trimIndent()
    }


    /**
     * ฟังก์ชันสำหรับอ่านข้อมูลจากไฟล์
     * @param path: เส้นทางของไฟล์ที่จะอ่าน
     * @return ข้อมูลที่อ่านจากไฟล์
     */
    private fun loadFromFile(path: String): String = File(path).readText(Charset.defaultCharset())


}