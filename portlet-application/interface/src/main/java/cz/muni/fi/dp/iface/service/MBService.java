package cz.muni.fi.dp.iface.service;

import java.util.List;

import cz.muni.fi.dp.iface.dto.MBMessageDTO;
import cz.muni.fi.dp.iface.dto.MBThreadDTO;

/**
 * Created by Tomas on 08.04.2016.
 */
public interface MBService {
//    MBMessageDTO createMessage(MBMessageDTO message);
    MBThreadDTO createThread(MBThreadDTO thread);

    MBThreadDTO updateThread(MBThreadDTO thread);

    MBThreadDTO getThreadById(long id);

    List<MBThreadDTO> getAllThreads();

    void deleteThread(long id);

    void deleteThread(MBThreadDTO thread);
}
