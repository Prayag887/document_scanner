package com.ag.documentscanner.components

import android.content.ClipData
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ag.documentscanner.presentation.home.PdfViewModel
import com.ag.documentscanner.utils.deleteFile
import com.ag.documentscanner.utils.getFileUri
import com.ag.documentscanner.utils.renameFile
import com.ag.documentscanner.utils.showToast
import java.util.Date

@Composable
fun PdfItemDialog(pdfViewModel: PdfViewModel = hiltViewModel()) {
    var newNameText by remember(pdfViewModel.currentPdf) { mutableStateOf(pdfViewModel.currentPdf?.name.orEmpty()) }
    val showRenameDialog by pdfViewModel.showRenameDialog.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (showRenameDialog) {
        Dialog(onDismissRequest = { pdfViewModel.onHideRenameDialog() }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Rename Pdf", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newNameText,
                        onValueChange = { newText -> newNameText = newText },
                        label = { Text("Pdf Name") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        IconButton(onClick = {
                            pdfViewModel.currentPdf?.let {
                                pdfViewModel.onHideRenameDialog()
                                val fileUri = getFileUri(context, it.name.orEmpty())
                                val shareIntent = Intent(Intent.ACTION_SEND)
                                shareIntent.type = "application/pdf"
                                shareIntent.clipData = ClipData.newRawUri("", fileUri)
                                shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
                                shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                context.startActivity(
                                    Intent.createChooser(
                                        shareIntent,
                                        "Share Pdf",
                                    ),
                                )
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }
                        IconButton(onClick = {
                            pdfViewModel.currentPdf?.let {
                                pdfViewModel.onHideRenameDialog()
                                if (deleteFile(context, it.name.orEmpty())) {
                                    pdfViewModel.deletePdf(it)
                                } else {
                                    context.showToast("Failed to delete file")
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { pdfViewModel.onHideRenameDialog() }) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            pdfViewModel.currentPdf?.let {
                                if (!it.name.equals(newNameText, true)) {
                                    pdfViewModel.onHideRenameDialog()
                                    renameFile(context, it.name.orEmpty(), newNameText)
                                    val updateFile =
                                        it.copy(name = newNameText, lastModifiedTime = Date())
                                    pdfViewModel.updatePdf(updateFile)
                                } else {
                                    pdfViewModel.onHideRenameDialog()
                                    context.showToast("Please enter a different name")
                                }
                            }
                        }) {
                            Text(text = "Update")
                        }
                    }
                }
            }
        }
    }
}
