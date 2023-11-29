package com.onlineauction.onlineauction.service;

import com.onlineauction.onlineauction.exception.CustomIllegalStateException;
import com.onlineauction.onlineauction.exception.ResourceNotFoundException;
import com.onlineauction.onlineauction.model.Auction;
import com.onlineauction.onlineauction.model.Bid;
import com.onlineauction.onlineauction.model.User;
import com.onlineauction.onlineauction.repository.AuctionRepository;
import com.onlineauction.onlineauction.repository.BidRepository;
import com.onlineauction.onlineauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserRepository userRepository;

    public Bid createBid(@Valid Bid bid, Long auctionId,Long userId) {
        // validation logic
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + auctionId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (bid.getBidAmount() <= auction.getMinimumPrice()) {
            throw new CustomIllegalStateException("Bid amount must be greater than minimum price");
        }

        if(!auction.isClosed()) {
            bid.setAuction(auction);
            bid.setUser(user);
            return bidRepository.save(bid);
        }
        else{
            throw new CustomIllegalStateException("Cannot place bid on a closed auction");

        }
    }
    public List<Bid> getBidsForAuction(Long auctionId) {
        // You can implement custom logic here if needed
        return (List<Bid>) bidRepository.findByAuctionId(auctionId);
    }
    public List<Bid> getAllBids() {
        // You can implement custom logic here if needed
        return (List<Bid>) bidRepository.findAll();
    }


    public Optional<Bid> getBidById(Long Id){
        return bidRepository.findById(Id);
    }

    public Bid updateBid(Long id,Bid updatedBid)
    {
        Optional<Bid> bid=bidRepository.findById(id);
        if(bid.isPresent()) {
            Bid originalBid = bidRepository.findById(id).get();
            Auction auction = originalBid.getAuction();
            if (!auction.isClosed()) {
                return bidRepository.findById(id)
                        .map(existingBid -> {
                            existingBid.setBidAmount(updatedBid.getBidAmount());
                            return bidRepository.save(existingBid);
                        })
                        .orElse(null);
            } else {
                throw new CustomIllegalStateException("Cannot place bid on a closed auction");
            }
        }
        else {
            throw new ResourceNotFoundException("Bid not found ");
        }
    }

    public Bid postWinningBidForAuction(Long auctionId) {
        //Closing Auction before declaring winner
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + auctionId));
        Bid WinningBid;
        if (!auction.isClosed()) {
            auction.setClosed(true);
            List<Bid> bids = bidRepository.findTopByAuctionIdOrderByBidAmountDesc(auctionId);
            WinningBid=bids.isEmpty() ? null : bids.get(0);
            auctionRepository.save(auction);
            return WinningBid;
        }
        //if auction is already closed then throw error
        else {
            throw new CustomIllegalStateException("Auction already closed");
             }

    }


    public Bid getWinningBidForAuction(Long auctionId) {
        //Closing Auction before declaring winner
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + auctionId));
        Bid WinningBid;
        if (!auction.isClosed()) {
            throw new CustomIllegalStateException("Auction not closed");
        }
        //if auction closed get the winner
        else {
            List<Bid> bids = bidRepository.findTopByAuctionIdOrderByBidAmountDesc(auctionId);
            WinningBid=bids.isEmpty() ? null : bids.get(0);
            return WinningBid;
        }

    }
    public void deleteBid(Long Id) {
        bidRepository.deleteById(Id);
    }

}
