package com.lucianoortizsilva.workshoppedido.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucianoortizsilva.workshoppedido.exception.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		BufferedImage bufferedImage = null;

		final String extensao = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(extensao) && !"jpg".equals(extensao)) {
			throw new FileException("Somente Imagens PNG e JPG são permitidas");
		}
		try {
			bufferedImage = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(extensao)) {
				bufferedImage = pngToJpg(bufferedImage);
			}
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}

		return bufferedImage;
	}

	private BufferedImage pngToJpg(BufferedImage bufferedImage) {
		BufferedImage jpgImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String extensao) {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, extensao, baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

}
