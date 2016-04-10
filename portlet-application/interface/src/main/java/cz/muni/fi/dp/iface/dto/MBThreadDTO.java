package cz.muni.fi.dp.iface.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tomas on 08.04.2016.
 */
@lombok.Data
public class MBThreadDTO implements Serializable {
    private long threadId;
    private long categoryId;
    private String title;
    private MBMessageDTO rootMessage;
    private List<MBMessageDTO> messages;
}
