package service.impl;

import pojo.Price;
import service.PriceProcessor;

public class BasicPriceProcessor implements PriceProcessor {
    @Override
    public Price process(Price price) {
        return price;
    }

}
