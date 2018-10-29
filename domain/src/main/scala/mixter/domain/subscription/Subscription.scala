package mixter.domain.subscription

import mixter.domain.EventPublisher
import mixter.domain.identity.UserId
import mixter.domain.subscription.event.{UserFollowed, UserUnfollowed}

case class Subscription(userFollowed: UserFollowed) {
  def unfollow()(implicit ep:EventPublisher): Unit = {
    ep.publish(UserUnfollowed(userFollowed.subscriptionId))
  }
}

object Subscription {
  def follow(follower: UserId, followee: UserId)(implicit ep:EventPublisher) : Unit = {
    ep.publish(UserFollowed(SubscriptionId(follower, followee)))
  }

}
