package ru.itmo.iandolzhanskii.sd.hw7.distribution.negative.sigma2;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.Sigma;

@Component
public class NegTwoSigma implements Sigma {
    
    public void accept() {
        System.out.println("[-2s, -1s)");
    }
}
