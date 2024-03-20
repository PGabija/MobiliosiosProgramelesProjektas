package eif.viko.lt.gposkaite.myFarm.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginEventChannel = Channel<String>(Channel.BUFFERED)
    val loginEvent = loginEventChannel.receiveAsFlow()

    fun onLoginSuccess(userId: String) {
        viewModelScope.launch {
            loginEventChannel.send(userId)
        }
    }
}