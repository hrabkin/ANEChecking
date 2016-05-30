//
//  ViewController.m
//  iOSNative
//
//  Created by Gennadiy Ryabkin on 5/30/16.
//  Copyright © 2016 zestug. All rights reserved.
//

#import "ViewController.h"

@interface ViewController () <CLLocationManagerDelegate, UIAlertViewDelegate> {
    CLLocationManager *locationManager;
}

@property (weak, nonatomic) IBOutlet UILabel *longitudeValue;

@property (weak, nonatomic) IBOutlet UILabel *latitudeValue;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    locationManager = [[CLLocationManager alloc] init];
    locationManager.delegate = self;
    locationManager.desiredAccuracy = kCLLocationAccuracyBest;
    locationManager.distanceFilter = 15; // meters
    [locationManager requestWhenInUseAuthorization];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)getMyLocation:(id)sender {
    
    CLAuthorizationStatus locationStatus = [CLLocationManager authorizationStatus];
    
    if (![CLLocationManager locationServicesEnabled] || locationStatus == kCLAuthorizationStatusDenied ||
        locationStatus == kCLAuthorizationStatusRestricted || locationStatus == kCLAuthorizationStatusNotDetermined)
    {
        NSString *title   = @"Location";
        NSString *message = @"To use location you must turn on 'Always' in the Location Services Settings";
        
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:title
                                                            message:message
                                                           delegate:self
                                                  cancelButtonTitle:@"Cancel"
                                                  otherButtonTitles:@"Settings", nil];
        [alertView show];
    }
    
    [locationManager startUpdatingLocation];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (buttonIndex == 1)
    {
        // Send the user to the Settings for this app
        NSURL *settingsURL = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
        [[UIApplication sharedApplication] openURL:settingsURL];
    }
}

#pragma mark - Location manager delegate


- (void)locationManager:(CLLocationManager *)manager
     didUpdateLocations:(NSArray *)locations {
    
    // If it's a relatively recent event, turn off updates to save power.
    CLLocation* location = [locations lastObject];
    NSDate* eventDate = location.timestamp;
    NSTimeInterval howRecent = [eventDate timeIntervalSinceNow];
    
    if (fabs(howRecent) < 15.0) {
        // If the event is recent, do something with it.
        NSLog(@"latitude %+.6f, longitude %+.6f\n",
              location.coordinate.latitude,
              location.coordinate.longitude);
        
        self.longitudeValue.text = [NSString stringWithFormat:@"%.6f", location.coordinate.longitude];
        self.latitudeValue.text = [NSString stringWithFormat:@"%.6f", location.coordinate.latitude];
    }
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error
{
    NSLog(@"didFailWithError: %@", error);
    UIAlertView *errorAlert = [[UIAlertView alloc]
                               initWithTitle:@"Error" message:@"Failed to Get Your Location" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [errorAlert show];
}

@end
