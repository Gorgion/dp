package cz.muni.fi.dp.iface.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Tomas on 08.04.2016.
 */
@Data
public class MBMessageDTO implements Serializable {
    private Long messageId;
    private Long threadId;
    private String title;
    private String body;
}
