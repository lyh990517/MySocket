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
            server.configureBlocking(false)
            while (true){
                val client = server.accept()
                while (client == null) {
                    Thread.sleep(100)
                    println("wait")
                    continue
                }
                val buffer = ByteBuffer.allocateDirect(1024)
                while ( client.read(buffer) == 0) {
                    println("read")
                }
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