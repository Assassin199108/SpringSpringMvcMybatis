package mvc5.config.resolver.genericconverter;

import mvc5.kit.DateKit;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 我的日期通用转换器
 *
 * @author Administrator
 */
@Component
public class MyDateGenericConvert implements GenericConverter {

    /**
     * 返回自定义的一系列
     * 返回该转换器可以转换的源和目标类型
     *
     * @return 原对象 目标对象的集合对
     */
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        Set<ConvertiblePair> pairSet = new HashSet<>(1);
        pairSet.add(new ConvertiblePair(String.class,Date.class));
        return pairSet;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (null == source){
            return null;
        }
        DateTimeFormat dateTimeFormat = targetType.getAnnotation(DateTimeFormat.class);
        // 使用默认的解析类(该解析支持时间戳和字符串)
        if (dateTimeFormat == null){
            return DateKit.parse(source.toString());
        }
        // 根据注解返回的值解析日期
        // TODO 暂时不考虑缓存
        // Format 最慢情况下 每秒也可以达到 20w 次 如果需要 可以用 ThreadLocal 缓存
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat.pattern());
        try {
            return simpleDateFormat.parse(source.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("日期 %s 和指定格式 %s 不匹配!", source, dateTimeFormat.pattern()), e);
        }

    }
}
