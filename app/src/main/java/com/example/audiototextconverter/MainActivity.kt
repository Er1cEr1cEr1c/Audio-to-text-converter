package com.example.audiototextconverter

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.audiototextconverter.ui.CircularImageCard
import com.example.audiototextconverter.ui.theme.AudioToTextConverterTheme
import java.util.Locale


val REQUEST_CODE_SPEECH_INPUT = 1

class MainActivity : ComponentActivity() {
    val textFieldValueState = mutableStateOf("")
    lateinit var clipboard: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        setContent {
            AudioToTextConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var generatedText by remember { textFieldValueState }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .weight(12f),
                            value = generatedText,
                            onValueChange = {
                                generatedText = it
                            },
                            shape = RoundedCornerShape(20.dp),
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp)
                                .weight(2f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularImageCard(
                                Modifier.clickable { captureAudio() },
                                R.drawable.ic_mic,
                                ""
                            )
                            Spacer(modifier = Modifier.padding(40.dp))

                            CircularImageCard(
                                Modifier.clickable { textFieldValueState.value = "" },
                                R.drawable.ic_reset,
                                ""
                            )
                            Spacer(modifier = Modifier.padding(40.dp))

                            CircularImageCard(
                                Modifier.clickable {
                                    val clip = ClipData.newPlainText(
                                        "your_text_to_be_copied",
                                        textFieldValueState.value
                                    )
                                    clipboard.setPrimaryClip(clip)
                                },
                                R.drawable.ic_copy,
                                ""
                            )
                        }
                    }
                }
            }
        }
    }

    fun captureAudio() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, " " + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK) {
            val result: ArrayList<String>? = data?.getStringArrayListExtra(
                RecognizerIntent.EXTRA_RESULTS
            )
            textFieldValueState.value = result?.get(0).toString()
        }
    }
}
