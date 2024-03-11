package br.com.fiapfood.adapters.gateway;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeGateway {

	byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException;

	void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException;

}
