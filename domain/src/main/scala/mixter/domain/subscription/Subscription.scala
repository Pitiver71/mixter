package mixter.domain.subscription

import mixter.domain.EventPublisher
import mixter.domain.identity.UserId
import mixter.domain.message.MessageId
import mixter.domain.subscription.event.{FolloweeMessageQuacked, UserFollowed, UserUnfollowed}

case class Subscription(userFollowed: UserFollowed) {
  def unfollow()(implicit ep:EventPublisher): Unit = {
    ep.publish(UserUnfollowed(userFollowed.subscriptionId))
  }

  def notifyFollower(messageId: MessageId)(implicit ep:EventPublisher) : Unit = {
    ep.publish(FolloweeMessageQuacked(userFollowed.subscriptionId, messageId))
  }
}

object Subscription {
  def follow(follower: UserId, followee: UserId)(implicit ep:EventPublisher) : Unit = {
    ep.publish(UserFollowed(SubscriptionId(follower, followee)))
  }

}
