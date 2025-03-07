package com.linketinder.mapper

import com.github.dozermapper.core.Mapper
import com.github.dozermapper.core.DozerBeanMapperBuilder

class ObjectMapper {
    static Mapper mapper = DozerBeanMapperBuilder.buildDefault()

    static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination)
    }

    static <O, D> List<D> parseListObject(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>()

        for(Object o : origin){
            destinationObjects.add(mapper.map(o, destination))
        }

        return destinationObjects
    }

}
