package com.onlineauction.onlineauction.controller;

import com.onlineauction.onlineauction.model.Bid;
import com.onlineauction.onlineauction.response.ResultResponse;
import com.onlineauction.onlineauction.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private BidService bidService;

    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);
    @PostMapping ("/auction/{auctionId}")
    public ResponseEntity<Object> postWinningBidForAuction(@PathVariable Long auctionId) {
        logger.info("Request received: Updating Auction Result");
        Bid winningBid = bidService.postWinningBidForAuction(auctionId);
        logger.info("Auction Result updated Successfully");
        return ResultResponse.postWinningBidBuilder(HttpStatus.ACCEPTED,winningBid);
    }

    @GetMapping("/auction/{auctionId}/winner")
    public ResponseEntity<Object> getWinningBidForAuction(@PathVariable Long auctionId) {
        logger.info("Request received: Getting Winner By AuctionId");
        Bid winningBid = bidService.getWinningBidForAuction(auctionId);
        logger.info("Winner details received Successfully for given Auction");
        return ResultResponse.getWinningBidBuilder(HttpStatus.OK,winningBid);
    }

}
