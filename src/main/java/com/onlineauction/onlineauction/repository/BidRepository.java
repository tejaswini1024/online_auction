package com.onlineauction.onlineauction.repository;

import com.onlineauction.onlineauction.model.Bid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BidRepository extends CrudRepository<Bid, Long> {
    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId AND b.bidAmount = (SELECT MAX(bidAmount) FROM Bid WHERE auction.id = :auctionId) ")
    List<Bid> findTopByAuctionIdOrderByBidAmountDesc(@Param("auctionId") Long auctionId );

    Iterable<Bid> findByAuctionId(Long auctionId);
    // custom queries if needed
}
