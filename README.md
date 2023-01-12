# Reward-System

Transaction.json file has been used to store the transaction data. which is available in resource source folder
Exposed endpoints : 3
RewardController -> GetMapping(value="/rewards") : to get all rewards, it will return a list of RewardDetails Model 
                    GetMapping(value="/customer-rewards/) with RequestParam id , which will return rewards of the particular customer
                    
TransactionController -> GetMapping(value="/transactions") : to Get all transcations which is stored in the json file
