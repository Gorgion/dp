package cz.muni.fi.dp.hook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceWrapper;

/**
 * Created by Tomas on 20.04.2016.
 */
public class MyMBMessageLocalServiceImpl extends MBMessageLocalServiceWrapper {
    public MyMBMessageLocalServiceImpl(MBMessageLocalService mbMessageLocalService) {
        super(mbMessageLocalService);
    }

    @Override
    public MBMessage getMBMessage(long messageId) throws PortalException, SystemException {
        MBMessage mbMessage = super.getMBMessage(messageId);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String date = sdf.format(new Date());

        mbMessage.setBody(mbMessage.getBody() + "\nDatum: " + date);

        return mbMessage;
    }
}
