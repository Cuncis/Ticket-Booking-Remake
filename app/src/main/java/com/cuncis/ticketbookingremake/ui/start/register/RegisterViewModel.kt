package com.cuncis.ticketbookingremake.ui.start.register

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuncis.ticketbookingremake.data.User
import com.cuncis.ticketbookingremake.ui.base.BaseViewModel
import com.cuncis.ticketbookingremake.util.Constants.KEY_USERNAME
import com.cuncis.ticketbookingremake.util.Resource
import com.cuncis.ticketbookingremake.util.getFileExtension
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel @ViewModelInject constructor() : BaseViewModel<RegisterNavigator>() {

    private val userCollectionPref = Firebase.firestore.collection("User")
    private val userStorage = Firebase.storage.reference.child("Photousers")

    private val _register = MutableLiveData<Resource<User>>()
    val register: LiveData<Resource<User>>
        get() = _register

    private val _checkUser = MutableLiveData<Resource<Boolean>>()
    val checkUser: LiveData<Resource<Boolean>>
        get() = _checkUser

    private val _uploadPhoto = MutableLiveData<Resource<Uri>>()
    val uploadPhoto: LiveData<Resource<Uri>>
        get() = _uploadPhoto

    fun register(user: User) = viewModelScope.launch {
        _register.postValue(Resource.loading(null))
        try {
            userCollectionPref.add(user).await()
            _register.postValue(Resource.success(user))
        } catch (e: Exception) {
            _register.postValue(Resource.error(e.message.toString(), null))
        }
    }

    fun checkUsername(username: String) = viewModelScope.launch {
        _checkUser.postValue(Resource.loading(null))
        try {
            val query = userCollectionPref
                .whereEqualTo("username", username)
                .get()
                .await()

            if (query.size() == 1) {
                _checkUser.postValue(Resource.success(true))
            } else {
                _checkUser.postValue(Resource.success(false))
            }
        } catch (e: Exception) {
            _checkUser.postValue(Resource.error(e.message.toString(), null))
        }
    }

    fun uploadPhotoUser(fileExtension: String?, photoLocation: Uri, username: String) = viewModelScope.launch {
        _uploadPhoto.postValue(Resource.loading(null))
        try {
            val storageRef = userStorage.child(username).child("${System.currentTimeMillis()}.${fileExtension}")
            storageRef.putFile(photoLocation).await()
            storageRef.downloadUrl.addOnSuccessListener {
                _uploadPhoto.postValue(Resource.success(it))
            }.addOnFailureListener {
                _uploadPhoto.postValue(Resource.error(it.message.toString(), null))
            }.await()
        } catch (e: Exception) {
            _uploadPhoto.postValue(Resource.error(e.message.toString(), null))
        }
    }
}























