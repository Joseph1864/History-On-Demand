package com.example.uitest.data

import com.example.uitest.data.local.DataLocalModule
import com.example.uitest.data.remote.DataRemoteModule

val dataModule = DataLocalModule + DataRemoteModule