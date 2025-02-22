/********* StatusBarHeight.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface StatusBarHeight : CDVPlugin {
  // Member variables go here.
}

- (void)echo:(CDVInvokedUrlCommand*)command;
- (void)getStatusBarHeight:(CDVInvokedUrlCommand*)command;
- (void)getNavigationBarHeight:(CDVInvokedUrlCommand*)command;
@end

@implementation StatusBarHeight

- (void)echo:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];

    if (echo != nil && [echo length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getStatusBarHeight:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = nil;
	CGRect statusRect = [[UIApplication sharedApplication] statusBarFrame];
	CGFloat statusBarHeight = statusRect.size.height;
	pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:statusBarHeight];

	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getNavigationBarHeight:(CDVInvokedUrlCommand*)command
{
	CDVPluginResult* pluginResult = nil;
	CGFloat navigationBarHeight = 0;
	pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:navigationBarHeight];

	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
