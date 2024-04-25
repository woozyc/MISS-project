package com.example.collectinformation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.collectinformation.ui.theme.CollectInformationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class AppActivity : ComponentActivity() {
    // onCreate方法是Activity的生命周期方法，在Activity创建时被调用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollectInformationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    // 调用编写的DisplayDeviceInfo函数，输出各类信息
                    DisplayAppInfo()
                }
            }
        }
    }
    @Composable
    fun DisplayAppInfo() {
        // 调用编写的DeviceInfoCollector中的函数，获取已安装应用程序信息
        val installedApps = DeviceInfoCollector.getInstalledApps(this)

        // 使用Column组件垂直排列各类信息，否则页面内字会重叠
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(R.drawable.___more_app),
                contentDescription = stringResource(R.string.appInfo),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(100.dp)
                    .offset(x = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text("Number of Installed Apps:")
            Text(installedApps)
        }
    }
}
