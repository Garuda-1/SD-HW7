package ru.itmo.iandolzhanskii.sd.hw7.distribution.negative.sigma3;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.Sigma;

@Component
public class NegThreeSigma implements Sigma {
    
    public void accept() {
        System.out.println("[-3s, -2s)");
    }
}
