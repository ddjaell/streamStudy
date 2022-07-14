package service.impl;

import pojo.Price;
import service.PriceProcessor;

public class TaxPriceProcessor implements PriceProcessor {
    @Override
    public Price process(Price price) {
        return new Price(price.getPrice() * 1.1);
    }
}
