package mvc5.config.resolver.genericconverter;

import com.alibaba.fastjson.JSON;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义String -> Map转换器
 *
 * @author Administrator
 */
@Component
public class MyMapConvert implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        HashSet<ConvertiblePair> pairs = new HashSet<>();
        pairs.add(new ConvertiblePair(String.class , Map.class));
        return pairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null){
            return null;
        }

        return JSON.toJavaObject(JSON.parseObject(source.toString()),targetType.getType());
    }

}
