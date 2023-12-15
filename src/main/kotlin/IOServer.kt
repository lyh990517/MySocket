import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.charset.StandardCharsets

class IOServer {
    fun openServer() {
        runCatching {
            println("open server")
            val server = ServerSocketChannel.open()
            server.bind(InetSocketAddress("localhost",8080))
            while (true){
                val client = server.accept()
                val buffer = ByteBuffer.allocateDirect(1024)
                client.read(buffer)
                buffer.flip()
                val body = StandardCharsets.UTF_8.decode(buffer)
                println(body)


                val response = ByteBuffer.wrap("im server".toByteArray())
                client.write(response)
                client.close()
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}