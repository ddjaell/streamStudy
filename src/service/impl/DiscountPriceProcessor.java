package service.impl;

import pojo.Price;
import service.PriceProcessor;

public class DiscountPriceProcessor implements PriceProcessor {
    @Override
    public Price process(Price price) {
        return new Price(price.getPrice() - 500);
    }
}
