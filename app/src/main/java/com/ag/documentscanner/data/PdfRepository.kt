package com.ag.documentscanner.data

import android.app.Application
import com.ag.documentscanner.data.local.PdfDatabase
import com.ag.documentscanner.data.model.Pdf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class PdfRepository(
    application: Application,
) {
    private val pdfDao = PdfDatabase.getInstance(application).pdfDao

    fun getAllPdf() = pdfDao.getAllPdf().flowOn(Dispatchers.IO)

    suspend fun insertPdf(pdf: Pdf) = pdfDao.insertPdf(pdf)

    suspend fun deletePdf(pdf: Pdf) = pdfDao.deletePdf(pdf)

    suspend fun updatePdf(pdf: Pdf) = pdfDao.updatePdf(pdf)
}
