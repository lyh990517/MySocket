import java.net.InetSocketAddress
import java.net.ServerSocket

class IOServer {
    fun openServer() {
        runCatching {
            println("open server")
            val server = ServerSocket()
            server.bind(InetSocketAddress("localhost",8080))
            while (true){
                val client = server.accept()
                val request = ByteArray(1024)
                val inputStream = client.getInputStream()
                inputStream.read(request)
                println(request.decodeToString())

                val outputStream = client.getOutputStream()
                val response = "im server"
                outputStream.write(response.toByteArray())
                outputStream.flush()
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}