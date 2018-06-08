package mvc5.config.resolver.genericconverter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.HashSet;
import java.util.Set;

/**
 * 个人自定义JSON转换器
 *
 * @author Administrator
 */
public class MyJsonConvert implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> pairSet = new HashSet<>(1);

        pairSet.add(new ConvertiblePair(String.class,JSONObject.class));
        pairSet.add(new ConvertiblePair(String.class,JSONArray.class));

        return pairSet;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null){
            return null;
        }
        if (targetType.getType() == JSONObject.class){
            return JSONObject.parse(source.toString());
        }
        if (targetType.getType() == JSONArray.class){
            return JSONArray.parse(source.toString());
        }

        return source;
    }
}
