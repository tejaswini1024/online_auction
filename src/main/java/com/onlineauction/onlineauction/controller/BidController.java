package com.onlineauction.onlineauction.controller;

import com.onlineauction.onlineauction.model.Auction;
import com.onlineauction.onlineauction.model.Bid;
import com.onlineauction.onlineauction.model.User;
import com.onlineauction.onlineauction.response.AuctionResponse;
import com.onlineauction.onlineauction.response.BidResponse;
import com.onlineauction.onlineauction.service.AuctionService;
import com.onlineauction.onlineauction.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bids")
public class BidController {
    @Autowired
    private BidService bidService;
    @Autowired
    private AuctionService auctionService;
    private static final Logger logger = LoggerFactory.getLogger(BidController.class);
    @PostMapping
    public ResponseEntity<Object> createBid(@RequestBody @Valid Bid bid, @RequestParam Long auctionId,@RequestParam Long userId) {
        logger.info("Request received: Creating Bid");
        Bid createdBid = bidService.createBid(bid, auctionId,userId);
        logger.info("Bid Created Successfully");
        return BidResponse.createdBidBuilder(HttpStatus.CREATED,createdBid);
    }
    @GetMapping("/auction/{auctionId}")
    public ResponseEntity<Object> getBidsForAuction(@PathVariable Long auctionId) {
        logger.info("Request received: Getting Bid By AuctionId");
        Optional<Auction> auction = auctionService.getAuctionById(auctionId);
        if (auction.isPresent()) {
            List<Bid> bids = bidService.getBidsForAuction(auctionId);
            logger.info("Bid details received Successfully");
            return BidResponse.getBidBuilder(HttpStatus.OK, bids);
        }
        else {
            logger.error("Auction not found with ID: {}", auctionId);
            return BidResponse.getBidBuilder(HttpStatus.NOT_FOUND, null);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllBids() {
        logger.info("Request received: Getting All Bids");
        List<Bid> bids = bidService.getAllBids();
        logger.info("Bid details received Successfully");
        return BidResponse.getAllBidsBuilder(HttpStatus.OK,bids);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Object> deleteBid(@PathVariable Long Id) {
        Optional<Bid> bid = bidService.getBidById(Id);
        logger.info("Request received: Deleting Bid");
        if (bid.isPresent()) {
            bidService.deleteBid(Id);
            logger.info("Bid Deleted Successfully");
            return BidResponse.deleteBidBuilder(HttpStatus.NO_CONTENT);
        }
        else {
            logger.error("Bid not found with ID: {}", Id);
            return BidResponse.deleteBidBuilder(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Object> updateBid(@PathVariable Long Id, @RequestBody Bid updatedBid) {
        logger.info("Request received: Updating Bid");
            Bid updated = bidService.updateBid(Id, updatedBid);
                logger.info("Updated Bid Successfully");
                return BidResponse.updateBidBuilder(HttpStatus.ACCEPTED);
    }


}
