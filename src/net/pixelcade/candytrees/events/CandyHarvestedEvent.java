package net.pixelcade.candytrees.events;

import java.util.SplittableRandom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.pixelcade.candytrees.CandyManager;
import net.pixelcade.candytrees.objects.Candy;
import net.pixelcade.candytrees.objects.CandyItem;

public class CandyHarvestedEvent extends Event {
	
	private final Player player;
    private final Candy candy;
	protected CandyManager manager;

    public CandyHarvestedEvent(Player player, Candy candy, CandyManager manager) {
        this.player = player;
        this.candy = candy;
        this.manager = manager;
    }

    public CandyItem getDrop() {
    	int randomNumber = new SplittableRandom().nextInt(this.manager.getTotalChance());
    	int counter = 0;
    	for (CandyItem candyItem : this.manager.candies) {
    		if (randomNumber <= candyItem.getChance() + counter) {
    			return candyItem;
    		}
    		counter = counter + candyItem.getChance();
    	}
    	return null;
    }
    
    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

	public Player getPlayer() {
		return player;
	}

	public Candy getCandy() {
		return candy;
	}

}
