package com.forest.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forest.entity.Product;
import com.forest.service.IProductService;

@Controller
public class ImageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	private IProductService						productService;
	
    // Constants 
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    // Properties 
    private final String UPLOAD_DIR = "/upload/img/";
	
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void products(@PathVariable Integer id, HttpServletResponse response) {
		Product p = productService.findById(id);
		try{
			
			if ((p == null) || (p.getImgSrc() == null)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			} else {
				/*
            // Decode the file name (might contain spaces and on) and prepare file object.
            File image = new File(getServletContext().getRealPath(UPLOAD_DIR), URLDecoder.decode(requestedImage, "UTF-8"));
            
            // Check if file actually exists in filesystem.
            if (!image.exists()) {
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            logger.info("Uploaded image already exists");
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
            }
            
            // Get content type by filename.
            String contentType = getServletContext().getMimeType(image.getName());
            
            // Check if file is actually an image (avoid download of other files by hackers!).
            // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
            if (contentType == null || !contentType.startsWith("image")) {
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            logger.info("Uploaded image doesn't look like a real image");
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
            }
				 */
				// Init servlet response.
				response.reset();
				response.setBufferSize(DEFAULT_BUFFER_SIZE);
				//response.setContentType("image/jpg");
				response.setHeader("Content-Length", String.valueOf(p.getImgSrc().length));
				response.setHeader("Content-Disposition", "inline; filename=\"" + p.getName() + "\"");
				
				// Prepare streams.
				ByteArrayInputStream byteInputStream = null;
				BufferedOutputStream output = null;
				
				try {
					// Open streams.
					byteInputStream = new ByteArrayInputStream(p.getImgSrc());
					output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
					
					// Write file contents to response.
					byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
					int length;
					while ((length = byteInputStream.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					}
				} finally {
					// close streams.
					close(output);
					close(byteInputStream);
				}
			}
		}catch (IOException e) {
			LOGGER.error("Problems during image resource manipulation.", 
                    e);
		}

    }
	
	// Helpers (can be refactored to public utility class) 
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                LOGGER.error("Problems during image resource manipulation.", 
                        e);
            }
        }
    }
}
