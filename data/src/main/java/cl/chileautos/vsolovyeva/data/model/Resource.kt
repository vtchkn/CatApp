package cl.chileautos.vsolovyeva.data.model


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    var throwable: Throwable? = null

    constructor(status: Status, data: T?, message: String?, throwable: Throwable) : this(status, data, message) {
        this.throwable = throwable
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, throwable.message, throwable)
        }
    }

    fun isSuccess(): Boolean {
        return status == Status.SUCCESS
    }


    fun isError(): Boolean {
        return status == Status.ERROR
    }
}