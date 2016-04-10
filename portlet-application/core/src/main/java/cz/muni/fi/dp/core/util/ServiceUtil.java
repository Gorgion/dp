package cz.muni.fi.dp.core.util;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomas on 08.04.2016.
 */
public class ServiceUtil {

    @Autowired
    private Mapper mapper;

    public <F, T> T convert(F from, final Class<T> toClass) {
        if (from == null) {
            return null;
        }
        return mapper.map(from, toClass);
    }
}
