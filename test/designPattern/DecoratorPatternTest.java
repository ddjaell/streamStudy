package designPattern;

import org.junit.Assert;
import org.junit.Test;
import pojo.Price;
import service.PriceProcessor;
import service.impl.BasicPriceProcessor;
import service.impl.DiscountPriceProcessor;
import service.impl.TaxPriceProcessor;

public class DecoratorPatternTest {

    @Test
    public void decoratorPatternTest() {
        Price originalPrice = new Price(Double.valueOf(5000));

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();
        PriceProcessor decoratedProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);

        Assert.assertEquals(Double.valueOf(4950), decoratedProcessor.process(originalPrice).getPrice());
    }
}
