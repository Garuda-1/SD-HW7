package ru.itmo.iandolzhanskii.sd.hw7.distribution.positive.sigma2;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.Sigma;

@Component
public class PosTwoSigma implements Sigma {
    
    public void accept() {
        System.out.println("[1s, 2s)");
    }
}
