package utils

import java.time.Instant

class JLog(private val classIdentifier: String) {
    fun log(msg: String){
        println("${Instant.now()} :: [$classIdentifier] >> $msg")
    }
    companion object{
        private val defaultLogger = JLog("")

        fun getLogger(logIdentifier : String) : JLog{
            return JLog(logIdentifier)
        }

        fun log(msg: String){
            defaultLogger.log(msg)
        }
    }
}