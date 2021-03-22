package br.com.zup.ecommerce.product.images;

import static br.com.zup.ecommerce.general.ConstantResponse.FIELD_CANNOT_BE_NULL;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ImageRequest {

    @Size(min = 1)
    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private List<MultipartFile> images = new ArrayList<>();


    public List<MultipartFile> getImages() {
        return images;
    }
    
    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
    
}
