package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Details;

import java.util.Optional;

public interface DetailsService {
    Details saveDefaultDetails();
    Details saveDetails(Details details);
    Details updateDetails(Details details);
    Optional<Details> findById(long id);
}
