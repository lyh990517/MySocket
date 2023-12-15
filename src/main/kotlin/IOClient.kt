import java.net.InetSocketAddress
import java.net.Socket

class IOClient {
    fun openSocket() {
        runCatching {
            val socket = Socket()
            socket.connect(InetSocketAddress("localhost", 8080))
            val outputStream = socket.getOutputStream()
            val body = "im client"
            outputStream.write(body.toByteArray())
            outputStream.flush()

            val inputStream = socket.getInputStream()
            val response = ByteArray(1024)
            inputStream.read(response)
            println(response.decodeToString().trim())
        }.onFailure {
            it.printStackTrace()
        }
    }

}