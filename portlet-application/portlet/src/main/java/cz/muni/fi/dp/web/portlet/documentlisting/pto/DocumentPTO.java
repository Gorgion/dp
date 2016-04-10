package cz.muni.fi.dp.web.portlet.documentlisting.pto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Tomas on 09.04.2016.
 */
@Data
@NoArgsConstructor
public class DocumentPTO implements Serializable {

    private String title;
    private String description;
    private MultipartFile file;
}
