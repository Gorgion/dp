package cz.muni.fi.dp.core;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBThreadLocalService;
import cz.muni.fi.dp.iface.dto.MBThreadDTO;
import cz.muni.fi.dp.iface.service.MBService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomas on 08.04.2016.
 */
public class MBServiceImpl implements MBService {

    @Autowired
    private MBThreadLocalService mbThreadLocalService;

    @Override
    public MBThreadDTO createThread(MBThreadDTO thread) {
        return null;
    }

    @Override
    public MBThreadDTO updateThread(MBThreadDTO thread) {
        return null;
    }

    @Override
    public MBThreadDTO getThreadById(long id) {
        return null;
    }

    @Override
    public List<MBThreadDTO> getAllThreads() {
//        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MBThread.class).add();

        return null;
    }

    @Override
    public void deleteThread(long id) {

    }

    @Override
    public void deleteThread(MBThreadDTO thread) {

    }
}
