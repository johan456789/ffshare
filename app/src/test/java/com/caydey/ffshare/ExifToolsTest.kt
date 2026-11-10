package com.caydey.ffshare

import com.caydey.ffshare.utils.ExifTools
import com.caydey.ffshare.utils.Utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExifToolsTest {
    @Test
    fun canReadExif_supportedTypes() {
        assertTrue(ExifTools.canReadExif(Utils.MediaType.JPEG))
        assertTrue(ExifTools.canReadExif(Utils.MediaType.JPG))
        assertTrue(ExifTools.canReadExif(Utils.MediaType.PNG))
        assertTrue(ExifTools.canReadExif(Utils.MediaType.WEBP))
        assertTrue(ExifTools.canReadExif(Utils.MediaType.HEIC))
        assertTrue(ExifTools.canReadExif(Utils.MediaType.HEIF))
    }

    @Test
    fun canReadExif_unsupportedTypes() {
        assertFalse(ExifTools.canReadExif(Utils.MediaType.GIF))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.MP4))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.MKV))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.WEBM))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.AVI))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.MP3))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.OGG))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.AAC))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.WAV))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.OPUS))
        assertFalse(ExifTools.canReadExif(Utils.MediaType.UNKNOWN))
    }

    @Test
    fun canWriteExif_supportedTypes() {
        assertTrue(ExifTools.canWriteExif(Utils.MediaType.JPEG))
        assertTrue(ExifTools.canWriteExif(Utils.MediaType.JPG))
        assertTrue(ExifTools.canWriteExif(Utils.MediaType.PNG))
        assertTrue(ExifTools.canWriteExif(Utils.MediaType.WEBP))
    }

    @Test
    fun canWriteExif_unsupportedTypes() {
        // HEIC/HEIF is never an output type (always converted to JPEG) and
        // ExifInterface cannot write HEIF anyway
        assertFalse(ExifTools.canWriteExif(Utils.MediaType.HEIC))
        assertFalse(ExifTools.canWriteExif(Utils.MediaType.HEIF))
        assertFalse(ExifTools.canWriteExif(Utils.MediaType.GIF))
        assertFalse(ExifTools.canWriteExif(Utils.MediaType.MP4))
        assertFalse(ExifTools.canWriteExif(Utils.MediaType.UNKNOWN))
    }

    @Test
    fun heicInputJpegOutput_allowsExifCopy() {
        // issue #1 scenario: HEIC input converted to JPEG output, EXIF copy must run
        val mediaType = Utils.MediaType.HEIC
        val outputMediaType = Utils.MediaType.JPEG
        assertTrue(ExifTools.canReadExif(mediaType) && ExifTools.canWriteExif(outputMediaType))
    }
}
