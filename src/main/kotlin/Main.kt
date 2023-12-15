import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        launch {
            repeat(10) {
                delay(100)
                IOClient().openSocket()
            }
        }
        launch {
            IOServer().openServer()
        }
    }
    Thread.sleep(1000)
}