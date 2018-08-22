#import "PgyerPlugin.h"
#import <PgyUpdate/PgyUpdateManager.h>
#import <PgySDK/PgyManager.h>


@implementation PgyerPlugin

-(void)crashRegister:(CDVInvokedUrlCommand *)command{
    
    NSString *ios_appid = [self getAppID];
    [[PgyManager sharedPgyManager] startManagerWithAppId:ios_appid];
}

-(void)checkUpdate:(CDVInvokedUrlCommand *)command{
    
    NSString *ios_appid = [self getAppID];
    
    [[PgyUpdateManager sharedPgyManager] startManagerWithAppId:ios_appid];
    [[PgyUpdateManager sharedPgyManager] checkUpdate];
}

-(void)showFeedback:(CDVInvokedUrlCommand *)command{
    
    NSString *ios_appid = [self getAppID];
    [[PgyManager sharedPgyManager] startManagerWithAppId:ios_appid];
    [[PgyManager sharedPgyManager] setFeedbackActiveType:kPGYFeedbackActiveTypeShake];
}

-(NSString*)getAppID
{
    NSString *ios_appid = @"";

    NSDictionary *info = [NSBundle mainBundle].infoDictionary;
    NSArray *urlTypes = info[@"CFBundleURLTypes"];
    for (NSDictionary *dict in urlTypes) {
        if([@"PgyerAppID" isEqualToString:dict[@"CFBundleURLName"]])
        {
            NSArray *schemes = dict[@"CFBundleURLSchemes"];
            ios_appid = schemes[0];
            break;
        }
    }
    return ios_appid;
}

@end

